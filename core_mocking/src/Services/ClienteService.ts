import { Cliente } from "../Models/Cliente";
import ClienteRepository from "../Repositories/ClienteRepository";


class ClienteService{
	private clienteRepository = ClienteRepository;

	async fetchClienteById(id: string): Promise<Cliente>{
		return this.clienteRepository.findClienteById(id);
	}

	async fetchClienteByDocument(document: string, documentType: string): Promise<Cliente>{
		return this.clienteRepository.findClienteByDocument(document, documentType);
	}
}

export default new ClienteService();
