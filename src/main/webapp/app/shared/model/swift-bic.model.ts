export interface ISwiftBic {
  id?: number;
  address?: string | null;
  address2?: string | null;
  address3?: string | null;
  address4?: string | null;
  bank?: string | null;
  bankName?: string | null;
  bankName2?: string | null;
  bankName3?: string | null;
  branch?: string | null;
  branchName?: string | null;
  branchName2?: string | null;
  city?: string | null;
  country?: string | null;
  location?: string | null;
  subTypeIndicator?: string | null;
  zip?: string | null;
}

export const defaultValue: Readonly<ISwiftBic> = {};
