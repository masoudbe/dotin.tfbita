import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IPurchaseFromOtherResources {
  id?: number;
  evidenceCode?: string | null;
  currencySupplierDescription?: string | null;
  amount?: number | null;
  purchaseRate?: number | null;
  orderRegistrationAmount?: number | null;
  requestDate?: string | null;
  confirmationDate?: string | null;
  description?: string | null;
  purchaseNumber?: string | null;
  purchaseCurrencyName?: string | null;
  purchaseFromOtherResources?: IOrderRegistrationInfo | null;
}

export const defaultValue: Readonly<IPurchaseFromOtherResources> = {};
