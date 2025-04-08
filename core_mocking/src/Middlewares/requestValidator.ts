import { NextFunction, Request, Response } from "express";
import { z, ZodError, ZodParsedType, ZodSchema } from "zod";

interface requestValidatorArgs {
	body?: ZodSchema,
	query?: ZodSchema,
	path?: ZodSchema,
}

export function requestValidator(args: requestValidatorArgs){
	if(!args.body){
		args.body = z.object({});
	}
	if(!args.path){
		args.path = z.object({});
	}
	if(!args.query){
		args.query = z.object({});
	}
	return (req: Request, res: Response, next: NextFunction) =>{
		try {
			args.path!.parse(req.params);
			args.query!.parse(req.query);
			args.body!.parse(req.body);
			next();
		} catch (err) {
			if(err instanceof ZodError){
				next(err.message);
				return;
			}
			next(err);
		}
	}
}
