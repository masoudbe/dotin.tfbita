import { IObjectiveCategory } from 'app/shared/model/objective-category.model';

export interface IObjectiveCategoryElement {
  id?: number;
  entityClass?: string | null;
  entityId?: number | null;
  objectiveCategory?: IObjectiveCategory | null;
}

export const defaultValue: Readonly<IObjectiveCategoryElement> = {};
