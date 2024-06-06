import { ICustom } from 'app/shared/model/custom.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IOrderRegistrationInfo {
  id?: number;
  orderRegNum?: string | null;
  startOrderRegDate?: string | null;
  endOrderRegDate?: string | null;
  requestNumber?: string | null;
  bankCode?: string | null;
  branchCode?: string | null;
  customerNumber?: string | null;
  applicantNationalcode?: string | null;
  performaNumber?: string | null;
  performaDate?: string | null;
  performaExpiryDate?: string | null;
  performaDatePersian?: string | null;
  performaExpiryDatePersian?: string | null;
  infoSubmissionDate?: string | null;
  sellerName?: string | null;
  beneficiaryCountry?: string | null;
  sourceCountry?: string | null;
  multipleTransportable?: boolean | null;
  deliveryTimeOfGoods?: string | null;
  totalWeightInKg?: number | null;
  possibilityCarrying?: boolean | null;
  possibilityClearance?: boolean | null;
  ableAddServiceInProductOrder?: boolean | null;
  freeZone?: boolean | null;
  currencyCode?: string | null;
  fobAmount?: number | null;
  discount?: number | null;
  shipmentCost?: number | null;
  othrCost?: number | null;
  totalAmount?: number | null;
  isFile?: boolean | null;
  fileNumber?: string | null;
  extended?: boolean | null;
  updated?: boolean | null;
  generateFromService?: boolean | null;
  certificateNumber?: string | null;
  centralBankCode?: string | null;
  isMigrational?: boolean | null;
  externalCustomer?: number | null;
  sumRedemptionDuration?: number | null;
  extendedDayNumber?: number | null;
  mainGroupProductCode?: string | null;
  producerCountries?: string | null;
  excelFileContentType?: string | null;
  excelFile?: string | null;
  commissionTransactionNumber?: string | null;
  customs?: ICustom[] | null;
  productInfos?: IProduct[] | null;
}

export const defaultValue: Readonly<IOrderRegistrationInfo> = {
  multipleTransportable: false,
  possibilityCarrying: false,
  possibilityClearance: false,
  ableAddServiceInProductOrder: false,
  freeZone: false,
  isFile: false,
  extended: false,
  updated: false,
  generateFromService: false,
  isMigrational: false,
};
