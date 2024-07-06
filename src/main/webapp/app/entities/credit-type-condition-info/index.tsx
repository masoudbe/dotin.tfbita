import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CreditTypeConditionInfo from './credit-type-condition-info';
import CreditTypeConditionInfoDetail from './credit-type-condition-info-detail';
import CreditTypeConditionInfoUpdate from './credit-type-condition-info-update';
import CreditTypeConditionInfoDeleteDialog from './credit-type-condition-info-delete-dialog';

const CreditTypeConditionInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CreditTypeConditionInfo />} />
    <Route path="new" element={<CreditTypeConditionInfoUpdate />} />
    <Route path=":id">
      <Route index element={<CreditTypeConditionInfoDetail />} />
      <Route path="edit" element={<CreditTypeConditionInfoUpdate />} />
      <Route path="delete" element={<CreditTypeConditionInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CreditTypeConditionInfoRoutes;
