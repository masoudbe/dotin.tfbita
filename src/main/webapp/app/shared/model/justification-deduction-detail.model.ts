import { ICategoryElement } from 'app/shared/model/category-element.model';
import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';

export interface IJustificationDeductionDetail {
  id?: number;
  deductionAmount?: number | null;
  equivalentDeductionAmount?: number | null;
  receiveCurrencyCode?: string | null;
  comment?: string | null;
  deductionReason?: ICategoryElement | null;
  justificationDeductionAmount?: IJustificationDeductionAmount | null;
}

export const defaultValue: Readonly<IJustificationDeductionDetail> = {};
