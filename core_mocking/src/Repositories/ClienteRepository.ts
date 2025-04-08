import db from "../Config/databaseConfig";
import { Cliente } from "../Models/Cliente";
import { DatabaseSync } from "node:sqlite";

class ClienteRepository {
	private db: DatabaseSync;

	constructor(db: DatabaseSync){
		this.db = db;
	}

	async findClienteById(id: string): Promise<Cliente>{
		return new Promise((resolve, reject)=>{
			const query = this.db.prepare("SELECT * FROM Clientes WHERE ID = ?");
			const cliente = query.get(id);
			if(!cliente){
				reject(new Error("Not Found"));
				return;
			}
			return resolve(cliente as Cliente);
		});
	}

	async findClienteByDocument(document: string, documentType: string): Promise<Cliente>{
		return new Promise((resolve, reject)=>{
			const query = this.db.prepare("SELECT * FROM Clientes WHERE NumeroDocumento = ? AND TipoDocumento = ?");
			const cliente = query.get(document, documentType);
			if(!cliente){
				reject(new Error("Not Found"));
			}
			return resolve(cliente as Cliente);
		});
	}
}

export default new ClienteRepository(db);
