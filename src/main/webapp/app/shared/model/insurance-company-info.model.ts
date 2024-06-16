import { IDraft } from 'app/shared/model/draft.model';

export interface IInsuranceCompanyInfo {
  id?: number;
  modificationDate?: string | null;
  name?: string | null;
  insuranceCompanyInfo?: IDraft | null;
}

export const defaultValue: Readonly<IInsuranceCompanyInfo> = {};
