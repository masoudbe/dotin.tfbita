import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftTax from './draft-tax';
import DraftTaxDetail from './draft-tax-detail';
import DraftTaxUpdate from './draft-tax-update';
import DraftTaxDeleteDialog from './draft-tax-delete-dialog';

const DraftTaxRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftTax />} />
    <Route path="new" element={<DraftTaxUpdate />} />
    <Route path=":id">
      <Route index element={<DraftTaxDetail />} />
      <Route path="edit" element={<DraftTaxUpdate />} />
      <Route path="delete" element={<DraftTaxDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftTaxRoutes;
