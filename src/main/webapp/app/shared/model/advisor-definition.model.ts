import { IDraft } from 'app/shared/model/draft.model';

export interface IAdvisorDefinition {
  id?: number;
  caption?: string | null;
  code?: string | null;
  countryIsoCode?: string | null;
  depositNum?: string | null;
  swiftCode?: string | null;
  creditDate?: string | null;
  bankCode?: string | null;
  branchCode?: string | null;
  defaultCurrencyCode?: string | null;
  countryCode?: string | null;
  advisingBank?: IDraft | null;
  interfaceAdvisingBank?: IDraft | null;
  coveringBank?: IDraft | null;
}

export const defaultValue: Readonly<IAdvisorDefinition> = {};
