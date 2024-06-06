import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IProduct {
  id?: number;
  code?: string | null;
  modificationDate?: string | null;
  name?: string | null;
  description?: string | null;
  topicCode?: string | null;
  attributeValueGroupName?: string | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<IProduct> = {};
