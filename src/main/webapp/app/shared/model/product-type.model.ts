import { ITypeAttribute } from 'app/shared/model/type-attribute.model';

export interface IProductType {
  id?: number;
  description?: string | null;
  modificationDate?: string | null;
  topicCode?: string | null;
  productTypeAttributes?: ITypeAttribute[] | null;
}

export const defaultValue: Readonly<IProductType> = {};
