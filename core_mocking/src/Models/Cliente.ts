import { z } from "zod";

export const TipoDocumentoSchema = z.enum(["CC", "CE", "PASAPORTE"]);

export const ClienteSchema = z.object({
  id: z.string().uuid(), // Validación de UUIDv4
  numeroDocumento: z.string().min(1), // No puede estar vacío
  tipoDocumento: TipoDocumentoSchema,
  nombreCompleto: z.string().min(1), // No puede estar vacío
  fechaNacimiento: z.string().regex(/^\d{4}-\d{2}-\d{2}$/).optional(), // Formato YYYY-MM-DD
  direccion: z.string().optional(),
  telefono: z.string().optional(),
  email: z.string().email().optional(),
  fechaRegistro: z.string().regex(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}Z$/), // ISO 8601
  estadoCuenta: z.string().optional(),
});

// Tipo TypeScript inferido desde el esquema Zod
export type Cliente = z.infer<typeof ClienteSchema>;
export type TipoDocumento = z.infer<typeof TipoDocumentoSchema>;
