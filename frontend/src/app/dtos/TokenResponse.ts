import { User } from "../models/User"

export interface TokenResponse{
    token: string;
    user:User;
}