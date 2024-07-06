export interface IJustificationDeductionAmount {
  id?: number;
  deductionAmount?: number | null;
  remainingDeductionAmount?: number | null;
  receivedDeductionAmount?: number | null;
}

export const defaultValue: Readonly<IJustificationDeductionAmount> = {};
