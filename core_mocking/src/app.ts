import { configDotenv } from "dotenv";
configDotenv()

import express from "express";
import morgan from "morgan";
import ClienteRouter from "./Routers/ClienteRouter";


const PORT = process.env.PORT;

const app = express();

app.use(express.json());
app.use(morgan("short"));

app.use("/api/v1/clientes", ClienteRouter);

app.listen(PORT, (err)=>{
	if(err){
		console.log(err);
		process.exit(1);
	}
	console.log(`Listening at port ${PORT}`)
});
