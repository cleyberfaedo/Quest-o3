package com.prova.questao3.controler;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class PacienteController {	

	private final PacienteService service;
	private final ModelMapper modelMapper;
	
	//Cria um novo paciente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	private EntityModel<BookDTO> adicionar(@Valid @RequestBody BookInput dto) {
		return toModel(service.save(toEntity(user)));
	}
	
	//"Retorna todos os pacientes
	@GetMapping
	public CollectionModel<EntityModel<BookDTO>> all(){
		List<EntityModel<BookDTO>> p = toCollectionModel(service.findAll());
		
		return new CollectionModel<>(p, 
				linkTo(methodOn(BookController.class).all()).withSelfRel());
	}
	
	//Retorna um paciente específico
	@GetMapping("/{id}")
	public EntityModel<BookDTO> one(@PathVariable Long id){		
		Book user = service.findById(id);		
		return toModel(user);
	}
	
	//Atualiza as informações do book 
	@PutMapping("/{id}")
	public EntityModel<BookDTO> upgrade(@PathVariable Long id, @Valid @RequestBody BookUpgradeInput body) {
		Paciente paciente = service.upgradePaciente(id, toEntity(body));
		return toModel(paciente);
	}
	
	//Deletar book
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}