import { Product } from "./product";
import { User } from "./User";

export interface WishList{
    id: number;
    products: Product[];
    user: User;
}