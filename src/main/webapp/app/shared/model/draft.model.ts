import { ICategoryElement } from 'app/shared/model/category-element.model';
import { ICustom } from 'app/shared/model/custom.model';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { IDraftStatusInfo } from 'app/shared/model/draft-status-info.model';
import { IInsuranceCompanyInfo } from 'app/shared/model/insurance-company-info.model';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { IAuditCompanyInfo } from 'app/shared/model/audit-company-info.model';
import { ITransportationType } from 'app/shared/model/transportation-type.model';
import { ICurrencyExchangeInfo } from 'app/shared/model/currency-exchange-info.model';
import { IDraftAccountInfo } from 'app/shared/model/draft-account-info.model';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { IProduct } from 'app/shared/model/product.model';
import { IStringValue } from 'app/shared/model/string-value.model';
import { ILongValue } from 'app/shared/model/long-value.model';
import { ISuggestedSanctionInfo } from 'app/shared/model/suggested-sanction-info.model';
import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';

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
  mainAccountCurrencyCode?: string | null;
  orderRegCurrencyCode?: string | null;
  chargedExchangeBrokerCurrencyCode?: string | null;
  destinationCountryCode?: string | null;
  beneficiaryCountryCode?: string | null;
  producerCountryCode?: string | null;
  registerationJustificationCurrencyCode?: string | null;
  branchCode?: string | null;
  operationalBranchCode?: string | null;
  certificateSenderBranchCode?: string | null;
  chargedExchangeBroker?: ICategoryElement | null;
  insuranceLetterType?: ICategoryElement | null;
  advisorDepositType?: ICategoryElement | null;
  interfaceAdvisorDepositType?: ICategoryElement | null;
  coveringAdvisorDepositType?: ICategoryElement | null;
  impartType?: ICategoryElement | null;
  dealType?: ICategoryElement | null;
  transportVehicleType?: ICategoryElement | null;
  freightLetterType?: ICategoryElement | null;
  actionCode?: ICategoryElement | null;
  ownershipCode?: ICategoryElement | null;
  currencyContainerPlace?: ICategoryElement | null;
  paymentType?: ICategoryElement | null;
  draftSource?: ICategoryElement | null;
  loadSwitchPlace?: ICustom | null;
  draftType?: IDraftType | null;
  statusInfo?: IDraftStatusInfo | null;
  insuranceCompanyInfo?: IInsuranceCompanyInfo | null;
  advisingBank?: IAdvisorDefinition | null;
  interfaceAdvisingBank?: IAdvisorDefinition | null;
  coveringBank?: IAdvisorDefinition | null;
  auditCompanyInfo?: IAuditCompanyInfo | null;
  transportType?: ITransportationType | null;
  currencyExchangeInfo?: ICurrencyExchangeInfo | null;
  accountInfo?: IDraftAccountInfo | null;
  destinationCustomCompanies?: ICustom | null;
  sourceCustomCompanies?: ICustom | null;
  services?: IServiceTariff[] | null;
  products?: IProduct[] | null;
  sanctionSerials?: IStringValue[] | null;
  customerNumbers?: ILongValue[] | null;
  suggestedSanctions?: ISuggestedSanctionInfo[] | null;
  documentTransactionContainers?: IDraftDocumentTransactionContainer[] | null;
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
