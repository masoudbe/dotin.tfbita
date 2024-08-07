import { ICategory } from 'app/shared/model/category.model';

export interface ICategoryElement {
  id?: number;
  value?: string | null;
  categoryName?: string | null;
  code?: string | null;
  category?: ICategory | null;
}

export const defaultValue: Readonly<ICategoryElement> = {};
