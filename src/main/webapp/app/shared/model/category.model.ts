export interface ICategory {
  id?: number;
  name?: string | null;
  code?: string | null;
}

export const defaultValue: Readonly<ICategory> = {};
