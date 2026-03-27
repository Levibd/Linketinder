import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroCandidato } from './cadastro-candidato';

describe('CadastroCandidato', () => {
  let component: CadastroCandidato;
  let fixture: ComponentFixture<CadastroCandidato>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroCandidato],
    }).compileComponents();

    fixture = TestBed.createComponent(CadastroCandidato);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
