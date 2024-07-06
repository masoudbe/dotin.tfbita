import { ICategoryElement } from 'app/shared/model/category-element.model';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';

export interface IAdvisorDefinitionDeposit {
  id?: number;
  advisorDepositNumber?: string | null;
  defaultAdvisorDeposit?: boolean | null;
  depositNum?: string | null;
  swiftCode?: string | null;
  currencyCode?: string | null;
  depositType?: ICategoryElement | null;
  advisorDefinition?: IAdvisorDefinition | null;
}

export const defaultValue: Readonly<IAdvisorDefinitionDeposit> = {
  defaultAdvisorDeposit: false,
};
