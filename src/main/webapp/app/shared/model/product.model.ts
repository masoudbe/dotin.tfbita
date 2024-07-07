import { IProductType } from 'app/shared/model/product-type.model';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';
import { IDraft } from 'app/shared/model/draft.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IProduct {
  id?: number;
  code?: string | null;
  modificationDate?: string | null;
  name?: string | null;
  productType?: IProductType | null;
  customJustifications?: ICustomJustification[] | null;
  drafts?: IDraft[] | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<IProduct> = {};
