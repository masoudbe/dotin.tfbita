export interface IAuditCompanyInfo {
  id?: number;
  address?: string | null;
  barCodes?: string | null;
  dateOfRegistration?: string | null;
  fax?: string | null;
  floor?: string | null;
  inernationalobserviation?: string | null;
  mainStreet?: string | null;
  mobile?: string | null;
  plaque?: string | null;
  postalCode?: string | null;
  registrationNumber?: string | null;
  telephone?: string | null;
  tempId?: number | null;
  theSideStreet?: string | null;
  title?: string | null;
  unit?: string | null;
  cityCode?: string | null;
}

export const defaultValue: Readonly<IAuditCompanyInfo> = {};
