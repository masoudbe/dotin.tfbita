import { ICategoryElement } from 'app/shared/model/category-element.model';
import { ITransportationType } from 'app/shared/model/transportation-type.model';
import { ICustom } from 'app/shared/model/custom.model';
import { IProduct } from 'app/shared/model/product.model';
import { IStringValue } from 'app/shared/model/string-value.model';

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
  beneficiaryCountryCode?: string | null;
  producerCountriesCode?: string | null;
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
  orderRegType?: ICategoryElement | null;
  requestType?: ICategoryElement | null;
  importType?: ICategoryElement | null;
  operationType?: ICategoryElement | null;
  currencyProvisionType?: ICategoryElement | null;
  paymentTool?: ICategoryElement | null;
  activityType?: ICategoryElement | null;
  ownerType?: ICategoryElement | null;
  status?: ICategoryElement | null;
  externalCustomerType?: ICategoryElement | null;
  transportVehicleType?: ICategoryElement | null;
  transportType?: ITransportationType | null;
  destCoustomers?: ICustom | null;
  cargoPlaceCustoms?: ICustom | null;
  entranceBorders?: ICustom | null;
  productInfos?: IProduct[] | null;
  commissionTransactionNumbers?: IStringValue[] | null;
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
