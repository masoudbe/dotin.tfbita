import { IProduct } from 'app/shared/model/product.model';
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';

export interface IDraftProductInfo {
  id?: number;
  productAmount?: string | null;
  productDimension?: string | null;
  product?: IProduct | null;
  draftReceipt?: IDraftReceipt | null;
}

export const defaultValue: Readonly<IDraftProductInfo> = {};
