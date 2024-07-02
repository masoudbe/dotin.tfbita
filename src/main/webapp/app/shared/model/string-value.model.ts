import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IStringValue {
  id?: number;
  value?: string | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<IStringValue> = {};
