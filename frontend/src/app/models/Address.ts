import { User } from "./User";

export interface Address{
    id: number;
    billingAddress: String;
    deliveryAddress: String;
    user: User;
}