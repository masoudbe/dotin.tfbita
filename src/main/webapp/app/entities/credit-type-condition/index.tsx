import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CreditTypeCondition from './credit-type-condition';
import CreditTypeConditionDetail from './credit-type-condition-detail';
import CreditTypeConditionUpdate from './credit-type-condition-update';
import CreditTypeConditionDeleteDialog from './credit-type-condition-delete-dialog';

const CreditTypeConditionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CreditTypeCondition />} />
    <Route path="new" element={<CreditTypeConditionUpdate />} />
    <Route path=":id">
      <Route index element={<CreditTypeConditionDetail />} />
      <Route path="edit" element={<CreditTypeConditionUpdate />} />
      <Route path="delete" element={<CreditTypeConditionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CreditTypeConditionRoutes;
