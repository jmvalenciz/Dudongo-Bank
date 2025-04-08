import { Router } from "express";
import { requestValidator } from "../Middlewares/requestValidator";
import { TipoDocumentoSchema } from "../Models/Cliente";
import ClienteService from "../Services/ClienteService";
import { z } from "zod";

const router = Router();

router.get("/",
	requestValidator({query: z.object({
		document: z.string(),
		documentType: TipoDocumentoSchema
	})}),
	async (req, res) =>{
		try {
			const {document, documentType } = req.query;
			const cliente = await ClienteService.fetchClienteByDocument(document as string, documentType as string);
			res.status(200).json(cliente);
		} catch (err: any){
			res.status(400).json({
				error: err.message
			});
		}
	}
);

router.get("/:id",
	requestValidator({path: z.object({
		id: z.string().uuid()
	})}),
	async (req, res) =>{
		try {
			const { id }= req.params;
			const cliente = await ClienteService.fetchClienteById(id);
			console.log(cliente);
			res.status(200).json(cliente);
		} catch (err: any) {
			res.status(400).json({
				error: err.message
			});
		}
	}
);

export default router;
