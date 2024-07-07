import { ICategoryElement } from 'app/shared/model/category-element.model';

export interface IAttribute {
  id?: number;
  modificationDate?: string | null;
  name?: string | null;
  format?: ICategoryElement | null;
  type?: ICategoryElement | null;
}

export const defaultValue: Readonly<IAttribute> = {};
