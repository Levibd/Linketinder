import { Chart, registerables } from 'chart.js';
import type { ICandidate, ICompany } from './interfaces';

// Registrar componentes do Chart.js
Chart.register(...registerables);

// --- ESTADO GLOBAL (Simulando Banco de Dados) ---
const candidates: ICandidate[] = [];
const companies: ICompany[] = [];
let skillsChart: Chart | null = null; 

// --- FUNÇÕES DE CADASTRO (CREATE) ---

const formCandidato = document.getElementById('form-candidato') as HTMLFormElement;
formCandidato.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const skillsInput = (document.getElementById('cand-skills') as HTMLInputElement).value;
    
    const newCandidate: ICandidate = {
        id: Date.now(),
        name: (document.getElementById('cand-name') as HTMLInputElement).value,
        email: (document.getElementById('cand-email') as HTMLInputElement).value,
        cpf: (document.getElementById('cand-cpf') as HTMLInputElement).value,
        age: parseInt((document.getElementById('cand-age') as HTMLInputElement).value),
        state: (document.getElementById('cand-state') as HTMLInputElement).value,
        description: (document.getElementById('cand-desc') as HTMLTextAreaElement).value,
        skills: skillsInput.split(',').map(s => s.trim().toUpperCase()) // Padroniza skills
    };

    candidates.push(newCandidate);
    alert('Candidato cadastrado com sucesso!');
    formCandidato.reset();
    updateCompanyView(); 
});

const formEmpresa = document.getElementById('form-empresa') as HTMLFormElement;
formEmpresa.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const vacanciesInput = (document.getElementById('emp-vacancies') as HTMLInputElement).value;

    const newCompany: ICompany = {
        id: Date.now(),
        name: (document.getElementById('emp-name') as HTMLInputElement).value,
        email: (document.getElementById('emp-email') as HTMLInputElement).value,
        cnpj: (document.getElementById('emp-cnpj') as HTMLInputElement).value,
        country: (document.getElementById('emp-country') as HTMLInputElement).value,
        state: (document.getElementById('emp-state') as HTMLInputElement).value,
        description: (document.getElementById('emp-desc') as HTMLTextAreaElement).value,
        vacancies: vacanciesInput.split(',').map(v => v.trim())
    };

    companies.push(newCompany);
    alert('Empresa cadastrada com sucesso!');
    formEmpresa.reset();
    updateCandidateView();
});

// --- FUNÇÕES DE VISUALIZAÇÃO (READ) ---

function updateCandidateView() {
    const container = document.getElementById('lista-vagas') as HTMLDivElement;
    container.innerHTML = '';

    companies.forEach(company => {
        company.vacancies.forEach(vacancy => {
            const card = document.createElement('div');
            card.className = 'card';
            
            card.innerHTML = `
                <h3>${vacancy}</h3>
                <p><strong>Empresa:</strong> *** Confidencial ***</p>
                <p><strong>Local:</strong> ${company.state} - ${company.country}</p>
                <p><em>${company.description}</em></p>
            `;
            container.appendChild(card);
        });
    });
}

function updateCompanyView() {
    
    const tbody = document.querySelector('#tabela-candidatos tbody') as HTMLTableSectionElement;
    tbody.innerHTML = '';

    candidates.forEach((cand, index) => {
        const row = document.createElement('tr');
        
        row.title = `ID: ${cand.id} | ${cand.description}`; 
        
        row.innerHTML = `
            <td>Candidato #${index + 1}</td> <td>${cand.skills.join(', ')}</td>
            <td>${cand.state}</td>
            <td><button class="btn-delete" data-id="${cand.id}">Excluir</button></td>
        `;
        tbody.appendChild(row);
    });

    
    document.querySelectorAll('.btn-delete').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const id = parseInt((e.target as HTMLButtonElement).getAttribute('data-id') || '0');
            deleteCandidate(id);
        });
    });

    
    updateChart();
}

// --- FUNÇÃO DE DELETE ---
function deleteCandidate(id: number) {
    if(confirm("Deseja deletar este candidato?")) {
        const index = candidates.findIndex(c => c.id === id);
        if (index > -1) {
            candidates.splice(index, 1);
            updateCompanyView();
        }
    }
}

// --- GRÁFICO (CHART.JS) ---
function updateChart() {
    const ctx = document.getElementById('skillsChart') as HTMLCanvasElement;
    
    
    const skillCount: Record<string, number> = {};
    
    candidates.forEach(cand => {
        cand.skills.forEach(skill => {
            if (skillCount[skill]) {
                skillCount[skill]++;
            } else {
                skillCount[skill] = 1;
            }
        });
    });

    const labels = Object.keys(skillCount);
    const data = Object.values(skillCount);

    
    if (skillsChart) {
        skillsChart.destroy();
    }

    skillsChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: '# Candidatos por Competência',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: { beginAtZero: true, ticks: { stepSize: 1 } }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `${context.raw} candidatos possuem esta skill`;
                        }
                    }
                }
            }
        }
    });
}