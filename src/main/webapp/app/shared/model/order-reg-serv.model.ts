import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IOrderRegServ {
  id?: number;
  amount?: number | null;
  currencyAmount?: number | null;
  unit?: string | null;
  title?: string | null;
  code?: string | null;
  orderRegService?: IOrderRegistrationInfo | null;
}

export const defaultValue: Readonly<IOrderRegServ> = {};
