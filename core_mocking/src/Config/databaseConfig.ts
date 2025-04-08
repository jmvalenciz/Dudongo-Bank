import { DatabaseSync } from "node:sqlite";

console.log(process.env.SQLITE_PATH);
const db: DatabaseSync = new DatabaseSync(process.env.SQLITE_PATH!);


export default db;
