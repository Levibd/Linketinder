import { Chart, registerables } from 'chart.js';
import type { ICandidate, ICompany } from './interfaces';

// Registrar componentes do Chart.js
Chart.register(...registerables);

// --- ESTADO GLOBAL (Simulando Banco de Dados) ---
const candidates: ICandidate[] = [];
const companies: ICompany[] = [];
let skillsChart: Chart | null = null; 

const RegexPatterns = {
    email: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/, // E-mail padrão
    cpf: /^\d{3}\.?\d{3}\.?\d{3}-?\d{2}$/, // 11 digitos, com ou sem ponto/traço
    cnpj: /^\d{2}\.?\d{3}\.?\d{3}\/?\d{4}-?\d{2}$/, // CNPJ padrão
    phone: /^\(?\d{2}\)?\s?9?\d{4}-?\d{4}$/, // Celular com ou sem DDD
    cep: /^\d{5}-?\d{3}$/, // CEP 12345-678 ou 12345678
    linkedin: /^https:\/\/(www\.)?linkedin\.com\/in\/[\w-]+\/?$/, // URL do LinkedIn
    tags: /^[\w\sà-úÀ-Ú+#]+(,[\w\sà-úÀ-Ú+#]+)*$/ // Palavras separadas por virgula
};

function validateField(value: string, regex: RegExp, fieldName: string): boolean {
    if (!regex.test(value)) {
        alert(`Erro de Validação: O campo [${fieldName}] está inválido!`);
        return false;
    }
    return true;
}

// --- FUNÇÕES DE CADASTRO (CREATE) ---

const formCandidato = document.getElementById('form-candidato') as HTMLFormElement;
formCandidato.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = (document.getElementById('cand-name') as HTMLInputElement).value;
    const email = (document.getElementById('cand-email') as HTMLInputElement).value;
    const cpf = (document.getElementById('cand-cpf') as HTMLInputElement).value;
    const phone = (document.getElementById('cand-phone') as HTMLInputElement).value;
    const linkedin = (document.getElementById('cand-linkedin') as HTMLInputElement).value;
    const skillsInput = (document.getElementById('cand-skills') as HTMLInputElement).value;
    const age = parseInt((document.getElementById('cand-age') as HTMLInputElement).value);
    const state = (document.getElementById('cand-state') as HTMLInputElement).value;
    const desc = (document.getElementById('cand-desc') as HTMLTextAreaElement).value;
    
    if (!validateField(email, RegexPatterns.email, "E-mail")) return;
    if (!validateField(cpf, RegexPatterns.cpf, "CPF")) return;
    if (!validateField(phone, RegexPatterns.phone, "Telefone")) return;
    if (!validateField(linkedin, RegexPatterns.linkedin, "LinkedIn")) return;
    if (!validateField(skillsInput, RegexPatterns.tags, "Skills")) return;
    


    const newCandidate: ICandidate = {
        id: Date.now(),
        name,
        email,
        cpf,
        age,
        state,
        phone,
        linkedin,
        description: desc,
        skills: skillsInput.split(',').map(s => s.trim().toUpperCase())
    };

    candidates.push(newCandidate);
    alert('Candidato cadastrado com sucesso!');
    formCandidato.reset();
    updateCompanyView(); 
});

const formEmpresa = document.getElementById('form-empresa') as HTMLFormElement;
formEmpresa.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const name = (document.getElementById('emp-name') as HTMLInputElement).value;
    const email = (document.getElementById('emp-email') as HTMLInputElement).value;
    const cnpj = (document.getElementById('emp-cnpj') as HTMLInputElement).value;
    const cep = (document.getElementById('emp-cep') as HTMLInputElement).value;
    const vacanciesInput = (document.getElementById('emp-vacancies') as HTMLInputElement).value;
    const country = (document.getElementById('emp-country') as HTMLInputElement).value;
    const state = (document.getElementById('emp-state') as HTMLInputElement).value;
    const desc = (document.getElementById('emp-desc') as HTMLTextAreaElement).value;

    if (!validateField(email, RegexPatterns.email, "E-mail Corporativo")) return;
    if (!validateField(cnpj, RegexPatterns.cnpj, "CNPJ")) return;
    if (!validateField(cep, RegexPatterns.cep, "CEP")) return;
    if (!validateField(vacanciesInput, RegexPatterns.tags, "Vagas")) return;


    const newCompany: ICompany = {
        id: Date.now(),
        name,
        email,
        cnpj,
        country,
        state,
        cep,
        description: desc,
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