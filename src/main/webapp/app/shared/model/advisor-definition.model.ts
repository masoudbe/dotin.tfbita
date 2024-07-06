import { IAdditionalBrokerInformation } from 'app/shared/model/additional-broker-information.model';
import { IAdvisorDefinitionDeposit } from 'app/shared/model/advisor-definition-deposit.model';
import { ITransferMethodManagement } from 'app/shared/model/transfer-method-management.model';
import { ISwiftBic } from 'app/shared/model/swift-bic.model';

export interface IAdvisorDefinition {
  id?: number;
  caption?: string | null;
  code?: string | null;
  countryIsoCode?: string | null;
  depositNum?: string | null;
  swiftCode?: string | null;
  defaultCurrencyCode?: string | null;
  currenciesCodes?: string | null;
  countryCode?: string | null;
  bankCode?: string | null;
  branchCode?: string | null;
  additionalBrokerInformation?: IAdditionalBrokerInformation | null;
  defaultVostroDeposit?: IAdvisorDefinitionDeposit | null;
  defaultNostroDeposit?: IAdvisorDefinitionDeposit | null;
  receiveMethod?: ITransferMethodManagement | null;
  payMethod?: ITransferMethodManagement | null;
  swiftBic?: ISwiftBic | null;
}

export const defaultValue: Readonly<IAdvisorDefinition> = {};
