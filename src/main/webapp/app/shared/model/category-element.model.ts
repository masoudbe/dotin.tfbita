import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IPurchaseFromOtherResources } from 'app/shared/model/purchase-from-other-resources.model';

export interface ICategoryElement {
  id?: number;
  value?: string | null;
  categoryName?: string | null;
  code?: string | null;
  orderRegType?: IOrderRegistrationInfo | null;
  requestType?: IOrderRegistrationInfo | null;
  importType?: IOrderRegistrationInfo | null;
  operationTyp?: IOrderRegistrationInfo | null;
  currencyProvisionType?: IOrderRegistrationInfo | null;
  paymentTool?: IOrderRegistrationInfo | null;
  activityType?: IOrderRegistrationInfo | null;
  ownerType?: IOrderRegistrationInfo | null;
  status?: IOrderRegistrationInfo | null;
  externalCustomerType?: IOrderRegistrationInfo | null;
  transportType?: IOrderRegistrationInfo | null;
  currencySupplier?: IPurchaseFromOtherResources | null;
  statusPurchase?: IPurchaseFromOtherResources | null;
  transportVehicleType?: IOrderRegistrationInfo | null;
}

export const defaultValue: Readonly<ICategoryElement> = {};
