package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	private Boolean system = true;
	
	public void inicial(Scanner scanner){
		
		while(system) {
			System.out.println("Qual ação de cargo deseja executar ?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Alterar");
			System.out.println("3 - Vizualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 0:
				system = false;	
				break;
			case 1:
				salvar(scanner);	
				break;
			case 2:
				alterar(scanner);
				break;
			case 3:
				vizualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				System.out.println("Opção inválida!");;	
				break;
			}
		}
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o Id do cargo que deseja excluir");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

	private void vizualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));	
	}

	private void salvar(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Descrição do cargo");
		String descricao = scanner.nextLine();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo!");
	}

	private void alterar(Scanner scanner) {
		System.out.println("Qual o id do cargo ?");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Nova descrição do cargo?");
		String descricao = scanner.nextLine();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Alterado!");
	}

}
