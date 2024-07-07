export interface ICustom {
  id?: number;
  modificationDate?: string | null;
  latinName?: string | null;
  name?: string | null;
  tempId?: number | null;
}

export const defaultValue: Readonly<ICustom> = {};
