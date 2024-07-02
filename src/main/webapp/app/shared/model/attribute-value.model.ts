import { ITypeAttribute } from 'app/shared/model/type-attribute.model';
import { IAttributeValueGroup } from 'app/shared/model/attribute-value-group.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IAttributeValue {
  id?: number;
  value?: string | null;
  customValue?: string | null;
  attributeValueGroupName?: string | null;
  typeAttribute?: ITypeAttribute | null;
  attributeValueGroup?: IAttributeValueGroup | null;
  product?: IProduct | null;
}

export const defaultValue: Readonly<IAttributeValue> = {};
