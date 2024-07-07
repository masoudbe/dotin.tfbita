import { ICategoryElement } from 'app/shared/model/category-element.model';

export interface ITransferMethodManagement {
  id?: number;
  code?: string | null;
  desc?: string | null;
  type?: ICategoryElement | null;
}

export const defaultValue: Readonly<ITransferMethodManagement> = {};
