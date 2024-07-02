import { IProduct } from 'app/shared/model/product.model';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IOrderRegServ } from 'app/shared/model/order-reg-serv.model';

export interface ILicenceInfo {
  id?: number;
  organizationLicence?: string | null;
  licenceNumber?: string | null;
  licenceDate?: string | null;
  havingProduct?: boolean | null;
  havingService?: boolean | null;
  creditDate?: string | null;
  product?: IProduct | null;
  service?: IServiceTariff | null;
  orderRegistrationInfo?: IOrderRegistrationInfo | null;
  orderRegServ?: IOrderRegServ | null;
}

export const defaultValue: Readonly<ILicenceInfo> = {
  havingProduct: false,
  havingService: false,
};
