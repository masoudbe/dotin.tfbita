import { ICustomJustification } from 'app/shared/model/custom-justification.model';

export interface ICustomJustificationChild {
  id?: number;
  item?: string | null;
  tariffCode?: string | null;
  productName?: number | null;
  productId?: number | null;
  boxCount?: number | null;
  boxType?: string | null;
  pureWeight?: number | null;
  impureWeight?: number | null;
  amountCurrency?: number | null;
  customJustification?: ICustomJustification | null;
}

export const defaultValue: Readonly<ICustomJustificationChild> = {};
