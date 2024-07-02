import { ICategory } from 'app/shared/model/category.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface ICategoryElement {
  id?: number;
  value?: string | null;
  categoryName?: string | null;
  code?: string | null;
  category?: ICategory | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<ICategoryElement> = {};
