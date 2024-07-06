import { IProductType } from 'app/shared/model/product-type.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IDraft } from 'app/shared/model/draft.model';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';

export interface IProduct {
  id?: number;
  code?: string | null;
  modificationDate?: string | null;
  name?: string | null;
  productType?: IProductType | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
  drafts?: IDraft[] | null;
  customJustifications?: ICustomJustification[] | null;
}

export const defaultValue: Readonly<IProduct> = {};
