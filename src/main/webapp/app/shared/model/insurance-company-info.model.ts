export interface IInsuranceCompanyInfo {
  id?: number;
  modificationDate?: string | null;
  name?: string | null;
}

export const defaultValue: Readonly<IInsuranceCompanyInfo> = {};
