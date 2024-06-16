import { ICustom } from 'app/shared/model/custom.model';
import { IProduct } from 'app/shared/model/product.model';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';

export interface IDraft {
  id?: number;
  advisorDepositNumber?: string | null;
  advisorRequestDeleted?: boolean | null;
  auditCost?: number | null;
  beneficiaryInfo?: string | null;
  branchStampCost?: number | null;
  centralBankCode?: string | null;
  centralBankCodeReference?: string | null;
  chargedExchangeBrokerAmount?: number | null;
  charterAcceptable?: boolean | null;
  comment?: string | null;
  completionDate?: string | null;
  coveringBankDepositNumber?: string | null;
  currencyExchangeDepositNumber?: string | null;
  customerDepositNumber?: string | null;
  deliverDuration?: number | null;
  discount?: number | null;
  draftNumber?: string | null;
  draftOrderRegProductWorth?: number | null;
  draftOrderRegServiceWorth?: number | null;
  draftOrderRegTotalWorth?: number | null;
  draftOtherCost?: number | null;
  hasTransportJustification?: boolean | null;
  insuranceCost?: number | null;
  insuranceDate?: string | null;
  insuranceExpiryDate?: string | null;
  insuranceNumber?: string | null;
  interfaceAdvisorDepositNumber?: string | null;
  issueDate?: string | null;
  issueDraftCommission?: number | null;
  lastShipmentDate?: string | null;
  mainCustomerNumber?: number | null;
  makeCertification?: boolean | null;
  multipleTransportable?: boolean | null;
  orderRegistrationDate?: string | null;
  orderRegistrationExpiryDate?: string | null;
  orderRegistrationNumber?: string | null;
  otherCost?: number | null;
  paymentAmount?: number | null;
  performaDate?: string | null;
  performaExpiryDate?: string | null;
  performaNumber?: string | null;
  postSwiftCost?: number | null;
  productSourceChangeable?: boolean | null;
  receiveAllCommission?: boolean | null;
  registerationJustificationAmount?: number | null;
  requestDate?: string | null;
  sanctionSerial?: string | null;
  serial?: number | null;
  shipmentCost?: number | null;
  sourceTransportPlace?: string | null;
  swiftComment?: string | null;
  transferAmount?: number | null;
  transportVehicleChangeable?: boolean | null;
  paymentTool?: string | null;
  letterNumberTazirat?: string | null;
  letterDateTazirat?: string | null;
  loanNumber?: string | null;
  isMigrational?: boolean | null;
  isNewCertificate?: boolean | null;
  isWithoutPayment?: boolean | null;
  destinationCountryCode?: string | null;
  beneficiaryCountryCode?: string | null;
  producerCountryCode?: string | null;
  branchCode?: string | null;
  operationalBranchCode?: string | null;
  certificateSenderBranchCode?: string | null;
  mainAccountCurrencyCode?: string | null;
  orderRegCurrencyCode?: string | null;
  chargedExchangeBrokerCurrency?: string | null;
  registerationJustificationCurrencyCode?: string | null;
  currencyExchangeInfoTitle?: string | null;
  transportationTypeName?: string | null;
  accountInfoCode?: string | null;
  customerNumbers?: number | null;
  sanctionSerials?: string | null;
  customs?: ICustom[] | null;
  products?: IProduct[] | null;
  services?: IServiceTariff[] | null;
}

export const defaultValue: Readonly<IDraft> = {
  advisorRequestDeleted: false,
  charterAcceptable: false,
  hasTransportJustification: false,
  makeCertification: false,
  multipleTransportable: false,
  productSourceChangeable: false,
  receiveAllCommission: false,
  transportVehicleChangeable: false,
  isMigrational: false,
  isNewCertificate: false,
  isWithoutPayment: false,
};
