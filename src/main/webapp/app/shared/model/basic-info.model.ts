export interface IBasicInfo {
  id?: number;
  applyDate?: string | null;
  code?: string | null;
  disabled?: boolean | null;
}

export const defaultValue: Readonly<IBasicInfo> = {
  disabled: false,
};
