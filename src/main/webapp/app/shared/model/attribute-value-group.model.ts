import { IAttribute } from 'app/shared/model/attribute.model';

export interface IAttributeValueGroup {
  id?: number;
  mandatory?: boolean | null;
  name?: string | null;
  attribute?: IAttribute | null;
}

export const defaultValue: Readonly<IAttributeValueGroup> = {
  mandatory: false,
};
