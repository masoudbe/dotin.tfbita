import { IProduct } from 'app/shared/model/product.model';
import { IOrderRegServ } from 'app/shared/model/order-reg-serv.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface ILicenceInfo {
  id?: number;
  organizationLicence?: string | null;
  licenceNumber?: string | null;
  licenceDate?: string | null;
  havingProduct?: boolean | null;
  havingService?: boolean | null;
  creditDate?: string | null;
  product?: IProduct | null;
  orderRegServ?: IOrderRegServ | null;
  orderRegistrationInfo?: IOrderRegistrationInfo | null;
}

export const defaultValue: Readonly<ILicenceInfo> = {
  havingProduct: false,
  havingService: false,
};
