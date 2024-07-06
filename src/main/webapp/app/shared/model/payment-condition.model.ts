export interface IPaymentCondition {
  id?: number;
  latinName?: string | null;
  name?: string | null;
}

export const defaultValue: Readonly<IPaymentCondition> = {};
