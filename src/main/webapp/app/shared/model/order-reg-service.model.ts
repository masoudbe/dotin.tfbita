import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IOrderRegService {
  id?: number;
  amount?: number | null;
  currencyAmount?: number | null;
  unit?: string | null;
  serviceTariff?: IServiceTariff | null;
  orderRegistrationInfo?: IOrderRegistrationInfo | null;
}

export const defaultValue: Readonly<IOrderRegService> = {};
