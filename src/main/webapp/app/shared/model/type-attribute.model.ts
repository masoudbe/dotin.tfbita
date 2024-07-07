import { IAttribute } from 'app/shared/model/attribute.model';
import { IProductType } from 'app/shared/model/product-type.model';

export interface ITypeAttribute {
  id?: number;
  necessary?: boolean | null;
  isUnique?: boolean | null;
  attribute?: IAttribute | null;
  productTypes?: IProductType[] | null;
}

export const defaultValue: Readonly<ITypeAttribute> = {
  necessary: false,
  isUnique: false,
};
