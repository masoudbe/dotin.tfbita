export interface ITransportationType {
  id?: number;
  latinName?: string | null;
  modificationDate?: string | null;
  name?: string | null;
}

export const defaultValue: Readonly<ITransportationType> = {};
